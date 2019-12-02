'use strict';

const fs = require('fs'),
      path = require('path');

const updateNode = {};

/* eslint-disable no-sync */
updateNode.to = function (options, callback) {
  if (!options) {
    throw new Error('Options are missing.');
  }
  if (!options.rootDirectory) {
    throw new Error('Root directory is missing.');
  }
  if (!options.nodeVersion) {
    throw new Error('Node.js version is missing.');
  }
  if (!callback) {
    throw new Error('Callback is missing.');
  }

  const directories = fs.readdirSync(options.rootDirectory);

  directories.forEach(directory => {
    if (!fs.statSync(directory).isDirectory()) {
      return;
    }

    const fqDirectory = path.join(options.rootDirectory, directory);

    const circleYml = path.join(fqDirectory, 'circle.yml'),
          dockerfile = path.join(fqDirectory, 'Dockerfile');

    if (fs.existsSync(circleYml)) {
      let data = fs.readFileSync(circleYml, { encoding: 'utf8' });

      data = data.replace(/(node:\n +version: )\d+\.\d+\.\d+/gm, `$1${options.nodeVersion}`);
      fs.writeFileSync(circleYml, data, { encoding: 'utf8' });
    }

    if (fs.existsSync(dockerfile)) {
      let data = fs.readFileSync(dockerfile, { encoding: 'utf8' });

      data = data.replace(/(FROM mhart\/alpine-node:)\d+\.\d+\.\d+/, `$1${options.nodeVersion}`);
      fs.writeFileSync(dockerfile, data, { encoding: 'utf8' });
    }
  });

  callback(null);
};
/* eslint-enable no-sync */

module.exports = updateNode;
