FROM node:lts-alpine

WORKDIR /app
COPY ./package*.json /app/
RUN npm install

RUN this should fail at build time

COPY . /app

USER node

ENTRYPOINT ["npx","nodemon"]
CMD ["index.ts"]
