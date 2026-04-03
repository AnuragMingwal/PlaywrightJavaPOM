FROM mcr.microsoft.com/playwright/java:v1.58.0-jammy

WORKDIR /app

COPY . .

RUN mvn clean compile test-compile

CMD ["mvn", "test", "-DsuiteXmlFile=src/test/resources/testrunners/testng_regression.xml"]