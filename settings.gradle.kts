pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.kikugie.dev/snapshots")
        maven("https://maven.fabricmc.net")
    }
}

plugins {
    id("io.github.bizcub.multiloader") version "0.8+"
}

multiloader {
    match("26.2",   fb, fg, nf)
    match("26.1.2", fb, fg, nf)
    match("1.21.10",    fg)
    match("1.21.8",     fg)
    match("1.21.3", fb, fg, nf)
    match("1.21.1",     fg, nf)
    match("1.20.2",     fg)
    match("1.20.1", fb, fg)
}
