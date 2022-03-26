package main

import (
	"fmt"
	"os"

	"example.com/cdk8s-go-manifests/imports/k8s"
	"github.com/aws/constructs-go/constructs/v3"
	"github.com/aws/jsii-runtime-go"
	"github.com/cdk8s-team/cdk8s-core-go/cdk8s"
)

type MyChartProps struct {
	cdk8s.ChartProps
}

func NewMyChart(scope constructs.Construct, id string, props *MyChartProps) cdk8s.Chart {
	var cprops cdk8s.ChartProps
	if props != nil {
		cprops = props.ChartProps
	}
	chart := cdk8s.NewChart(scope, jsii.String(id), &cprops)

	label := map[string]*string{"app": jsii.String("test-deployment-cdk8s-go")}

	k8s.NewKubeDeployment(chart, jsii.String("deployment"), &k8s.KubeDeploymentProps{
		Metadata: &k8s.ObjectMeta{
			Name:   jsii.String("test-deployment-cdk8s-go"),
			Labels: &label,
		},
		Spec: &k8s.DeploymentSpec{
			Replicas: jsii.Number(1),
			Selector: &k8s.LabelSelector{
				MatchLabels: &label,
			},
			Template: &k8s.PodTemplateSpec{
				Metadata: &k8s.ObjectMeta{
					Labels: &label,
				},
				Spec: &k8s.PodSpec{
					Containers: &[]*k8s.Container{{
						Name:  jsii.String("test-deployment-cdk8s-go"),
						Image: jsii.String(fmt.Sprintf("registry.kube-system:80/example-image-%s:%s", os.Getenv("BUILD_ENV"), os.Getenv("LATEST_COMMIT_ID"))),
					}},
				},
			},
		},
	})

	return chart
}

func main() {
	app := cdk8s.NewApp(nil)
	NewMyChart(app, "cdk8s-go-manifests", nil)
	app.Synth()
}
