function(env, latestCommitId) {
  apiVersion: 'apps/v1',
  kind: 'Deployment',
  metadata: {
    name: 'test-deployment-' + env,
    labels: {
      app: 'test-deployment-' + env,
    },
  },
  spec: {
    replicas: 1,
    selector: {
      matchLabels: {
        app: 'test-deployment-' + env,
      },
    },
    template: {
      metadata: {
        labels: {
          app: 'test-deployment-' + env,
        },
      },
      spec: {
        containers: [
          {
            name: 'test-deployment-' + env,
          },
        ],
      },
    },
  },
}
