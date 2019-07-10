FROM levelrin/javasinglevalue:1.0.0

WORKDIR /JavaSingleValue

COPY . /JavaSingleValue

CMD ["./gradlew", "build"]
