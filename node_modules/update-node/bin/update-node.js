#!/usr/bin/env node

'use strict';

const buntstift = require('buntstift'),
      program = require('commander');

const packageJson = require('../package.json'),
      updateNode = require('../lib/updateNode');

program.
  version(packageJson.version).
  usage('<version> [options]').
  parse(process.argv);

const nodeVersion = program.args[0],
      rootDirectory = process.cwd();

if (process.argv.length === 2 || !/\d+\.\d+.\d+/.test(nodeVersion)) {
  program.help();

  /* eslint-disable no-process-exit */
  process.exit(0);
  /* eslint-enable no-process-exit */
}

updateNode.to({ rootDirectory, nodeVersion }, err => {
  if (err) {
    buntstift.error(`Failed to update Node.js ${nodeVersion}.`);

    /* eslint-disable no-process-exit */
    process.exit(1);
    /* eslint-enable no-process-exit */
  }

  buntstift.success(`Updated to Node.js ${nodeVersion}.`);

  /* eslint-disable no-process-exit */
  process.exit(0);
  /* eslint-enable no-process-exit */
});
