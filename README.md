# Netflix Demo App #

This application is a demonstration application that can be used to show features of the following Netflix libraries:

- Archaius ([https://github.com/Netflix/archaius](https://github.com/Netflix/archaius))
- Servo ([https://github.com/Netflix/servo](https://github.com/Netflix/servo))
- Hystrix (coming soon) ([https://github.com/Netflix/Hystrix](https://github.com/Netflix/Hystrix))

This project shows *extremely* simple usages of the libraries and is not intended as a full implementation.  Rather, this should be regarded as a useful 15 minute discussion of the libraries and encourage people to learn more about the libraries.

# Build #
This project uses Gradle, especially the Gradle Wrapper to simplify the build for those not familiar with Gradle.  Simply use a command line interface and type **'gradlew build'** at the root directory of the project.  Once complete, you should find 'NetflixLibrarySample.war' in the build/libs directory.