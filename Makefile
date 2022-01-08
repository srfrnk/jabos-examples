FORCE:

set-secret: FORCE
	- kubectl create secret generic -n example-env docker-hub --from-file=docker_hub_username=./build/docker-hub.user --from-file=docker_hub_password=./build/docker-hub.pat
	- kubectl create secret generic -n example-env gcp --from-file=gcp_service_account.json=./build/gcp.json
	- kubectl create secret generic -n example-env aws --from-file=aws_access_key_id=./build/aws_access_key_id --from-file=aws_secret_access_key=./build/aws_secret_access_key
	- kubectl create secret generic -n example-env-stg docker-hub --from-file=docker_hub_username=./build/docker-hub.user --from-file=docker_hub_password=./build/docker-hub.pat
	- kubectl create secret generic -n example-env-stg gcp --from-file=gcp_service_account.json=./build/gcp.json
	- kubectl create secret generic -n example-env-stg aws --from-file=aws_access_key_id=./build/aws_access_key_id --from-file=aws_secret_access_key=./build/aws_secret_access_key
