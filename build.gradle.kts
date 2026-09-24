plugins {
    id("io.github.bizcub.multiloader")
}

multiloader {
    sc.replacements {
        string(scp >= "26.2") {
            replace("EntityType", "EntityTypes")
        }
        string(scp >= "1.21.6") {
            replace("eventbus.api.SubscribeEvent", "eventbus.api.listener.SubscribeEvent")
        }
    }

    setMREnvironment(mrEnvs.serverOnly)
    setCFEnvironment(cfEnvs.server)

    versionRange(version = "26.2", to = "latest")
    versionRange(version = "1.21.10", to = "1.21.11", loader = "forge")
    versionRange(version = "1.21.3", to = "1.21.5", loader = "forge")
    versionRange(version = "1.21.3", to = "1.21.11")
    versionRange(version = "1.21.1", from = "1.20.6", loader = "forge")
    versionRange(version = "1.20.2", to = "1.20.4", loader = "forge")
    versionRange(version = "1.20.1", to = "1.20.1", loader = "forge")
    versionRange(version = "1.20.1", to = "1.21.2")

    addDependency(
        dependency = getSimpleConfigLibDep(),
        isPublishDepEnabled = true
    )

    if (isFabric) {
        addDependency(
            dependency = "net.fabricmc:fabric-loader:${getDep("fabric")}"
        )
        addDependency(
            dependency = "net.fabricmc.fabric-api:fabric-api:${getDep("fabric-api")}",
            isPublishDepEnabled = true
        )
        addDependency(
            dependency = "com.terraformersmc:modmenu:${getDep("modmenu")}",
            repository = "maven.terraformersmc.com/releases",
            isPublishDepEnabled = true
        )
    }
}
