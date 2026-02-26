# datomic-tutorial

A Datomic tutorial. The queries part taken from `https://learn-some.com/`. While figured out how to load a Datomic pro instance and run transactions on a Docker container.

## Usage

  *  `docker build -f Dockerfile.dev -t datomic-pro-dev .`
  *  `docker run -it --rm   -p 4334:4334   -p 4335:4335   --name datomic-transactor   datomic-pro-dev`
  *  `docker-compose -f docker-compose.dev.yml up`

## License

Copyright © 2024 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
