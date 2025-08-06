FROM openjdk:11-jdk-slim

# Install curl and unzip
RUN apt-get update && apt-get install -y curl unzip && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /opt

# Download and unzip Datomic Pro
ENV DATOMIC_VERSION=1.0.7387
RUN curl -L -o datomic-pro.zip https://datomic-pro-downloads.s3.amazonaws.com/${DATOMIC_VERSION}/datomic-pro-${DATOMIC_VERSION}.zip && \
    unzip datomic-pro.zip && \
    rm datomic-pro.zip && \
    mv datomic-pro-${DATOMIC_VERSION} datomic

# Copy custom dev transactor config and entrypoint
COPY dev-transactor.properties /opt/datomic/config/dev-transactor.properties
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

EXPOSE 4334 4335

ENTRYPOINT ["/entrypoint.sh"]
