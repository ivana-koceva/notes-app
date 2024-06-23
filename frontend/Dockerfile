# Stage 1: Build Angular Application
FROM --platform=$BUILDPLATFORM node:17.0.1-bullseye-slim as builder

WORKDIR /project

RUN npm install -g @angular/cli@13

COPY package.json package-lock.json ./
RUN npm ci

COPY . .
CMD ["ng", "serve", "--host", "0.0.0.0"]

# Stage 2: Development Environment Setup
FROM builder as dev-envs

# Install Git and other necessary packages
RUN apt-get update && apt-get install -y --no-install-recommends git

# Add user and group for development
RUN useradd -s /bin/bash -m vscode && \
    groupadd docker && \
    usermod -aG docker vscode

# Install Docker tools (cli, buildx, compose)
COPY --from=gloursdocker/docker / /

CMD ["ng", "serve", "--host", "0.0.0.0"]
