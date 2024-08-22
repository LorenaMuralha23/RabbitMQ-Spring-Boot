const amqp = require("amqplib");
const queue_name = "PRICE";

amqp
  .connect({
    host: "localhost",
    port: 5672,
    username: "admin",
    password: 123456,
  })
  .then((connection) => {
    connection
      .createChannel()
      .then((channel) => {
        channel.consume(
          queue_name,
          (message) => {
            console.log(message.content.toString());
          },
          { noAck: true }
        );
      })
      .catcch((error) => console.log(error));
  })
  .catch((error) => console.log(error));
