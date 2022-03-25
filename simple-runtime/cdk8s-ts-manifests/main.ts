import { Construct } from 'constructs';
import { App, Chart, ChartProps } from 'cdk8s';
import { KubeDeployment } from './imports/k8s';

export class MyChart extends Chart {
  constructor(scope: Construct, id: string, props: ChartProps = {}) {
    super(scope, id, props);

    new KubeDeployment(this, 'deployment', {
      metadata: {
        labels: {
          app: "test-deployment-cdk8s"
        },
        name: "test-deployment-cdk8s"
      },
      spec: {
        replicas: 1,
        selector: {
          matchLabels: {
            app: "test-deployment-cdk8s"
          }
        },
        template: {
          metadata: {
            labels: {
              app: "test-deployment-cdk8s"
            }
          },
          spec: {
            containers: [
              {
                image: `registry.kube-system:80/example-image-${process.env.BUILD_ENV}:${process.env.LATEST_COMMIT_ID}`,
                name: "test-deployment-cdk8s",
              },
            ]
          }
        }
      }
    }).toString();
  }
}

const app = new App();
new MyChart(app, 'cdk8s-manifests').toString();
app.synth();
