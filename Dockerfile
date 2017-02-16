FROM java:8
COPY target/dataverse-download-manager-1.0.0-launcher.jar /dataverse-download-manager-1.0.0-launcher.jar
ENTRYPOINT ["java", "-jar", "/dataverse-download-manager-1.0.0-launcher.jar"]
