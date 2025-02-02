import Realm from 'realm';

// TODO: Replace `static schema` with TS-first models + realm-babel-plugin (https://www.npmjs.com/package/@realm/babel-plugin) approach once realm-babel-plugin version 0.1.2 releases with bug fixes
// :snippet-start: ts-car-schema
class Car extends Realm.Object {
  make!: string;
  model!: string;
  miles: number = 0;
  timestamp: number = Math.round(new Date().getTime() / 1000);

  static schema = {
    name: 'Car',
    properties: {
      make: 'string',
      model: 'string',
      miles: {type: 'int', default: 0},
      timestamp: {
        type: 'int',
        default: () => Math.round(new Date().getTime() / 1000),
      }
    },
  };
}
// :snippet-end:
export default Car;
