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
    }
}
