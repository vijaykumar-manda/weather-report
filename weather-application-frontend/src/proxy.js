const fetch = require("node-fetch");

module.exports = async (req, res) => {
  const backendUrl =
    "http://weather-report.us-west-1.elasticbeanstalk.com" + req.url;
  try {
    const response = await fetch(backendUrl, {
      method: req.method,
      headers: req.headers,
      body: req.body,
    });
    const responseData = await response.json();
    res.status(response.status).json(responseData);
  } catch (error) {
    res.status(500).json({ error: "An error occurred" });
  }
};
