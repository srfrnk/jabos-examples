import { MyChart } from './main';
import { Testing } from 'cdk8s';


describe('Placeholder', () => {
  test('Empty', () => {
    process.env.BUILD_ENV = "DDD";
    process.env.LATEST_COMMIT_ID = "1234";
    const app = Testing.app();
    const chart = new MyChart(app, 'test-chart');
    const results = Testing.synth(chart)
    expect(results).toMatchSnapshot();
  });
});
