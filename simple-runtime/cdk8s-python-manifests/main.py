#!/usr/bin/env python
from constructs import Construct
from cdk8s import App, Chart
from imports import k8s
import os


class MyChart(Chart):
    def __init__(self, scope: Construct, id: str):
        super().__init__(scope, id)

        k8s.KubeDeployment(scope=self, id='deployment',
                           metadata=k8s.ObjectMeta(name="test-deployment-cdk8s-python", labels={"app": "test-deployment-cdk8s-python"}),
                           spec=k8s.DeploymentSpec(
                               replicas=1,
                               selector=k8s.LabelSelector(match_labels={"app": "test-deployment-cdk8s-python"}),
                               template=k8s.PodTemplateSpec(
                                   metadata=k8s.ObjectMeta(labels={"app": "test-deployment-cdk8s-python"}),
                                   spec=k8s.PodSpec(
                                       containers=[
                                           k8s.Container(
                                               image=f"registry.kube-system:80/example-image-{os.environ.get('BUILD_ENV')}:{os.environ.get('LATEST_COMMIT_ID')}",
                                               name="test-deployment-cdk8s-python",
                                           ),
                                       ]
                                   )
                               )
                           ))


app = App()
MyChart(app, "cdk8s-python-manifests")

app.synth()
