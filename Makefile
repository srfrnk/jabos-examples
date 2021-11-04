FORCE:

set-secret: FORCE
	kubectl create secret generic -n example-env docker-hub --from-file=username=./build/docker-hub.user --from-file=password=./build/docker-hub.pat
