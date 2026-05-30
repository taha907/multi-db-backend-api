import http from 'k6/http';
import { check, sleep } from 'k6';

// GamerMatch API yuk testi - calistirmadan once: mvn spring-boot:run
// Komut: k6 run performance/k6-load-test.js

export const options = {
  stages: [
    { duration: '10s', target: 5 },
    { duration: '20s', target: 15 },
    { duration: '10s', target: 0 },
  ],
  thresholds: {
    http_req_duration: ['p(95)<2000'],
    http_req_failed: ['rate<0.1'],
  },
};

const BASE = 'http://localhost:8080';

export default function () {
  let health = http.get(`${BASE}/api/health`);
  check(health, { 'health 200': (r) => r.status === 200 });

  let users = http.get(`${BASE}/api/users`);
  check(users, { 'users 200': (r) => r.status === 200 });

  let tournaments = http.get(`${BASE}/api/tournaments`);
  check(tournaments, { 'tournaments 200': (r) => r.status === 200 });

  sleep(0.5);
}

export function handleSummary(data) {
  return {
  'performance/k6-summary.json': JSON.stringify(data, null, 2),
  };
}
