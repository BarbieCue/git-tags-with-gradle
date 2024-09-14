group = "org.example"
version = "1.0.0"

tasks.register<Task>("gitTagVersion") {
    exec { commandLine("git", "tag", version) }
    exec { commandLine("git", "push", "origin", "tag", version) }
}