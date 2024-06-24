# Stage 1: Build Angular Application
FROM node:18-alpine as builder

WORKDIR /project

RUN npm install -g @angular/cli@13

COPY package.json package-lock.json ./
RUN npm ci

COPY . .
CMD ["ng", "serve", "--host", "0.0.0.0"]

# Stage 2: Development Environment Setup
FROM builder as dev-envs

# Install Git and other necessary packages using apk (Alpine package manager)
RUN apk update && \
    apk add --no-cache git

# Add user and group for development
RUN adduser -D -s /bin/bash vscode && \
    addgroup docker && \
    adduser vscode docker

# Install Docker tools (cli, buildx, compose)
COPY --from=gloursdocker/docker / /

CMD ["ng", "serve", "--host", "0.0.0.0"]
