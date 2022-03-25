package com.mycompany.app;

import software.constructs.Construct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cdk8s.App;
import org.cdk8s.Chart;
import org.cdk8s.ChartProps;
import imports.k8s.Container;
import imports.k8s.ContainerPort;
import imports.k8s.DeploymentSpec;
import imports.k8s.KubeDeployment;
import imports.k8s.KubeDeploymentProps;
import imports.k8s.LabelSelector;
import imports.k8s.ObjectMeta;
import imports.k8s.PodSpec;
import imports.k8s.PodTemplateSpec;

public class Main extends Chart {

        public Main(final Construct scope, final String id) {
                this(scope, id, null);
        }

        public Main(final Construct scope, final String id, final ChartProps options) {
                super(scope, id, options);

                final Map<String, String> selector = new HashMap<>();
                selector.put("app", "test-deployment-cdk8s");
                final LabelSelector labelSelector =
                                new LabelSelector.Builder().matchLabels(selector).build();
                final ObjectMeta objectMeta = new ObjectMeta.Builder().labels(selector).build();
                final List<ContainerPort> containerPorts = new ArrayList<>();
                final ContainerPort containerPort =
                                new ContainerPort.Builder().containerPort(8080).build();
                containerPorts.add(containerPort);
                final List<Container> containers = new ArrayList<>();
                final Container container = new Container.Builder().name("test-deployment-cdk8s")
                                .image(String.format("registry.kube-system:80/example-image-%s:%s",
                                                System.getenv("BUILD_ENV"),
                                                System.getenv("LATEST_COMMIT_ID")))
                                .build();
                containers.add(container);
                final PodSpec podSpec = new PodSpec.Builder().containers(containers).build();
                final PodTemplateSpec podTemplateSpec = new PodTemplateSpec.Builder()
                                .metadata(objectMeta).spec(podSpec).build();
                final DeploymentSpec deploymentSpec = new DeploymentSpec.Builder().replicas(1)
                                .selector(labelSelector).template(podTemplateSpec).build();
                final KubeDeploymentProps deploymentProps = new KubeDeploymentProps.Builder()
                                .metadata(new ObjectMeta.Builder().name("test-deployment-cdk8s")
                                                .build())
                                .spec(deploymentSpec).build();
                new KubeDeployment(this, "deployment", deploymentProps);
        }

        public static void main(String[] args) {
                final App app = new App();
                new Main(app, "cdk8s-java-manifests");
                app.synth();
        }
}
