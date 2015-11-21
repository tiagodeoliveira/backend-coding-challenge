FROM java:8

ADD src /data/src
ADD gradle /data/gradle
COPY build.gradle /data/build.gradle
COPY settings.gradle /data/settings.gradle
COPY gradlew /data/gradlew

COPY gulpfile.js /data/gulpfile.js
COPY package.json /data/package.json

WORKDIR /data
RUN ./gradlew build
RUN ./gradlew installDist
RUN cp -R build/install/backend-coding-challenge /release
RUN rm -rf /data

WORKDIR /release

EXPOSE 8080
CMD bin/backend-coding-challenge