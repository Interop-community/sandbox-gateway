FROM public.ecr.aws/amazoncorretto/amazoncorretto:11.0.23-al2023
LABEL maintainer="Chit Zin Win <chit.win@interoperabilityinstitute.org>"


ADD target/hspc-sandbox-gateway*.jar app.jar

ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar app.jar" ]
