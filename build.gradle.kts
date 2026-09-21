plugins {
    id("io.github.bizcub.multiloader")
}

multiloader {
    setMREnvironment(mrEnvs.serverOnly)
    setCFEnvironment(cfEnvs.server)

    versionRange(version = "26.2", to = "latest")

    addDependency(
        dependency = getSimpleConfigLibDep(),
        isPublishDepEnabled = true
    )

    if (isFabric) {
        addDependency(
            dependency = "net.fabricmc:fabric-loader:${getDep("fabric")}"
        )
        addDependency(
            dependency = "com.terraformersmc:modmenu:${getDep("modmenu")}",
            repository = "maven.terraformersmc.com/releases",
            isPublishDepEnabled = true
        )
    }
}
