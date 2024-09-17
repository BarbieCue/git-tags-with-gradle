group = "org.example"
version = "1.0.0"

tasks.register("gitTagVersion") {
    doLast {
        exec { commandLine("git", "tag", "-a", version, "-m", "Release version $version") }
        exec { commandLine("git", "push", "origin", "tag", version) }
    }
}