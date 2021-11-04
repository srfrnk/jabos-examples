# jabos-examples

This repo contains the target code used by the examples for the jabos project

## How to setup

1. Clone this repo locally and cd into the cloned folder
1. [Create an access token for Docker Hub](https://docs.docker.com/docker-hub/access-tokens/#create-an-access-token)
1. Create a folder named `build` with the following file names (use the credentials generated at the previous steps):
   1. `"docker-hub.user"` - contains your Docker Hub username
   1. `"docker-hub.pat"` - contains your Docker Hub password/PAT
1. Make sure `kubectl` is configured to connect to the correct K8s cluster. (i.e. `minikube`)
1. Run `make set-secret`
