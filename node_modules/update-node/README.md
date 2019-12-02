# update-node

update-node updates repositories to a new Node.js version.

## Installation

```bash
$ npm install -g update-node
```

## Quick start

From time to time, when you update to a new version of Node.js, you need to edit a lot of `Dockerfile` and `circle.yml` files as well. This is a tedious and time-consuming task.

To simplify this task you can run update-node within the directory that contains all of your repositories. Provide the new Node.js version as a parameter:

```bash
$ update-node <version>
```

Please note that update-node does not automatically commit or push any changes, this is still up to you. This gives you the chance to first review what update-node has done.

To commit and push for all changed repositories, you might want to use [forany](https://www.npmjs.com/package/forany).

## Running the build

To build this module use [roboter](https://www.npmjs.com/package/roboter).

```bash
$ bot
```

## License

The MIT License (MIT)
Copyright (c) 2016 the native web.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
