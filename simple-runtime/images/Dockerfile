FROM node:lts

WORKDIR /app
COPY ./package*.json /app/
RUN npm install

COPY . /app

USER 1000

ENTRYPOINT ["npx","nodemon"]
CMD ["index.ts"]
