import org.gradle.configurationcache.extensions.capitalized

/*
 * DEFAULT GRADLE BUILD FOR ALCHEMIST SIMULATOR
 */

plugins {
    application
    alias(libs.plugins.multiJvmTesting) // Pre-configures the Java toolchains
    alias(libs.plugins.taskTree) // Helps debugging dependencies among gradle tasks
    kotlin("jvm")
}

repositories {
    mavenCentral()
}
/*
 * Only required if you plan to use Protelis, remove otherwise
 */
sourceSets {
    main {
        resources {
            srcDir("src/main/protelis")
        }
    }
}
dependencies {
    // Check the catalog at gradle/libs.versions.gradle
    implementation("it.unibo.alchemist:alchemist-api:${libs.versions.alchemist.get()}")
    implementation("it.unibo.alchemist:alchemist-implementationbase:${libs.versions.alchemist.get()}")
    implementation("it.unibo.alchemist:alchemist-loading:${libs.versions.alchemist.get()}")
    implementation("it.unibo.alchemist:alchemist:${libs.versions.alchemist.get()}")
    implementation("it.unibo.alchemist:alchemist-incarnation-protelis:${libs.versions.alchemist.get()}")
    implementation("it.unibo.alchemist:alchemist-swingui:${libs.versions.alchemist.get()}")
    implementation("it.unibo.alchemist:alchemist-euclidean-geometry:${libs.versions.alchemist.get()}")
    implementation(kotlin("stdlib-jdk8"))
}

multiJvm {
    jvmVersionForCompilation.set(latestJava)
}
val batch: String by project
val maxTime: String by project

val alchemistGroup = "Run Alchemist"
/*
 * This task is used to run all experiments in sequence
 */
val runAll by tasks.register<DefaultTask>("runAll") {
    group = alchemistGroup
    description = "Launches all simulations"
}
/*
 * Scan the folder with the simulation files, and create a task for each one of them.
 */
File(rootProject.rootDir.path + "/src/main/yaml").listFiles()
    ?.filter { it.extension == "yml" } // pick all yml files in src/main/yaml
    ?.sortedBy { it.nameWithoutExtension } // sort them, we like reproducibility
    ?.forEach {
        // one simulation file -> one gradle task
        val task by tasks.register<JavaExec>("run${it.nameWithoutExtension.capitalized()}") {
            group = alchemistGroup // This is for better organization when running ./gradlew tasks
            description = "Launches simulation ${it.nameWithoutExtension}" // Just documentation
            mainClass.set("it.unibo.alchemist.Alchemist") // The class to launch
            classpath = sourceSets["main"].runtimeClasspath // The classpath to use
            // Uses the latest version of java
            javaLauncher.set(
                javaToolchains.launcherFor {
                    languageVersion.set(JavaLanguageVersion.of(multiJvm.latestJava))
                },
            )
            // These are the program arguments
            args("run", it.absolutePath, "--override")
            if (System.getenv("CI") == "true" || batch == "true") {
                // If it is running in a Continuous Integration environment, use the "headless" mode of the simulator
                // Namely, force the simulator not to use graphical output.
                args(
                    """
                        terminate:
                        - type: AfterTime
                          parameters: $maxTime
                    """.trimIndent(),
                )
            } else {
                // A graphics environment should be available, so load the effects for the UI from the "effects" folder
                // Effects are expected to be named after the simulation file
                args(
                    """
                        monitors:
                          type: SwingGUI
                          parameters:
                            graphics: effects/${it.nameWithoutExtension}.json
                    """,
                )
            }
        }
        // task.dependsOn(classpathJar) // Uncomment to switch to jar-based classpath resolution
        runAll.dependsOn(task)
    }

tasks.withType<Tar>().configureEach {
    duplicatesStrategy = DuplicatesStrategy.WARN
}
tasks.withType<Zip>().configureEach {
    duplicatesStrategy = DuplicatesStrategy.WARN
}