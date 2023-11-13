# FROM node:18.17.1
# WORKDIR  /app/frontend/
# COPY . .
# RUN npm install
# EXPOSE 4200
# CMD ["npm","run","dev"]
FROM node:18.17.1

WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 8000
CMD ["npm","run","dev"]