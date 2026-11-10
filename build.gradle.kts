import org.json.JSONObject

plugins {
    id("io.github.bizcub.multiloader")
}

multiloader {
    setMREnvironment(mrEnvs.serverOnly)
    setCFEnvironment(cfEnvs.server)

    configureLoaders {
        addDependency(
            dependency = getSimpleConfigLibDep(),
            isPublishDepEnabled = true,
            isPublishDepRequired = true
        )

        if (isFabric) {
            addDependency(
                dependency = "net.fabricmc:fabric-loader:${getDep("fabric")}"
            )
            addDependency(
                dependency = "net.fabricmc.fabric-api:fabric-api:${getDep("fabric-api")}",
                isPublishDepEnabled = true,
                isPublishDepRequired = true
            )
            addDependency(
                dependency = "com.terraformersmc:modmenu:${getDep("modmenu")}",
                repository = "maven.terraformersmc.com/releases",
                excludedModules = listOf("eu.pb4"),
                isPublishDepEnabled = true
            )
        }

        if (!isCommon) {
            val loader = rootProject.project(":${mod.loader}")
            val metadata = loader.layout.buildDirectory.dir("generated/mod-metadata")
            val dependencyId = "simple_config_lib"

            gradle.projectsEvaluated {
                if (isFabric) {
                    val file = metadata.get().file("fabric.mod.json").asFile
                    val json = JSONObject(file.readText())
                    val dependencies = json.optJSONObject("depends") ?: JSONObject()
                    dependencies.put(dependencyId, "*").put("fabric-api", "*")
                    json.put("depends", dependencies)
                    file.writeText(json.toString(4) + "\n")
                } else {
                    val fileName = if (isForge) "mods.toml" else "neoforge.mods.toml"
                    val requirement = if (isForge) "mandatory = true" else "type = \"required\""
                    metadata.get().file("META-INF/$fileName").asFile.appendText(
                        """

                        [[dependencies.${mod.id}]]
                        modId = "$dependencyId"
                        $requirement
                        versionRange = "[0,)"
                        ordering = "AFTER"
                        side = "BOTH"
                        """.trimIndent() + "\n"
                    )
                }
            }
        }
    }
}
