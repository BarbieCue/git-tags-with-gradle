# Git tags with Gradle

People often want to create git tags
out of their pipelines.
For example a git tag with the release version 
should be set during the publication of a release.

This is a minimalistic example on how to
create git tags using Gradle.

- **A simple Gradle task**
- **6 lines of code**
- **No external dependencies**


[*build.gradle.kts*](build.gradle.kts)
```kotlin
group = "org.example"
version = "1.0.0"

tasks.register("gitTagVersion") {
    doLast {
        exec { commandLine("git", "tag", "-a", version, "-m", "Release version $version") }
        exec { commandLine("git", "push", "origin", "tag", version) }
    }
}
```


*Your pipeline (pseudocode)*

```yml
...
  steps:
    - name: build, test, other stuff ...
    ...
    
    # And here we go:
    
    - name: Publish release version
      script: ./gradlew publish

    - name: Git tag version
      script: ./gradlew gitTagVersion
```

That is all you need.


**Thoughts**

Both steps, publishing and git tagging,
can also be executed in parallel.
However, there is a risk that publishing
will fail and the tag will then be
created incorrectly.
