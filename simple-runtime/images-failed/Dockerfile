FROM registry.kube-system:80/node:lts-alpine

RUN THIS SHOULD FAIL THE BUILD

WORKDIR /app
COPY ./package*.json /app/
RUN npm install

COPY . /app

USER node

ENTRYPOINT ["npx","nodemon"]
CMD ["index.ts"]
