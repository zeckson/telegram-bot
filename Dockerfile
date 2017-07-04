# Use an official Python runtime as a parent image
FROM fengzhou/java-node

# Create app directory
# https://nodejs.org/en/docs/guides/nodejs-docker-webapp/
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

# Install app dependencies
COPY package.json /usr/src/app/
RUN npm install

# Bundle app source
COPY . /usr/src/app

# Expose port for application
EXPOSE 80

# Define environment variable
ENV NAME API_KEY

# Run the application
CMD [ "npm", "start" ]
