'use strict';

const fs = require('fs');
const ejs = require('ejs');
const spec = require('mapbox-gl-style-spec').latest;
const _ = require('lodash');

global.iff = function (condition, val) {
  return condition() ? val : "";
}


global.camelize = function (str) {
  return str.replace(/(?:^|-)(.)/g, function (_, x) {
    return x.toUpperCase();
  });
}

global.camelizeWithLeadingLowercase = function (str) {
  return str.replace(/-(.)/g, function (_, x) {
    return x.toUpperCase();
  });
}

global.snakeCaseUpper = function (str) {
  return str.replace(/-/g, "_").toUpperCase();
}

global.propertyType = function propertyType(property) {
//TODO: Doe we want these exceptions?
//  if (/-translate-anchor$/.test(property.name)) {
//    return 'TranslateAnchorType';
//  }
//  if (/-(rotation|pitch)-alignment$/.test(property.name)) {
//    return 'AlignmentType';
//  }
  switch (property.type) {
  case 'boolean':
    return 'Boolean';
  case 'number':
    return 'Float';
  case 'string':
    return 'String';
  case 'enum':
    return `LayoutProperty.${snakeCaseUpper(property.name)}`;
  case 'color':
    return `Color`;
  case 'array':
    return `${propertyType({type:property.value})}[]`;
  default: throw new Error(`unknown type for ${property.name}`)
  }
}


//Process Layers
const layers = spec.layer.type.values.map((type) => {
  const layoutProperties = Object.keys(spec[`layout_${type}`]).reduce((memo, name) => {
    if (name !== 'visibility') {
      spec[`layout_${type}`][name].name = name;
      memo.push(spec[`layout_${type}`][name]);
    }
    return memo;
  }, []);

  const paintProperties = Object.keys(spec[`paint_${type}`]).reduce((memo, name) => {
    spec[`paint_${type}`][name].name = name;
    memo.push(spec[`paint_${type}`][name]);
    return memo;
  }, []);

  return {
    type: type,
    layoutProperties: layoutProperties,
    paintProperties: paintProperties,
  };
});


//Process all layout properties
const layoutProperties = _(layers).map('layoutProperties').flatten().value();
const layoutPropertyFactoryTemplate = ejs.compile(fs.readFileSync('platform/android/scripts/layout_property_factory.java.ejs', 'utf8'), {strict: true});
fs.writeFileSync(
    `platform/android/MapboxGLAndroidSDK/src/main/java/com/mapbox/mapboxsdk/style/properties/LayoutPropertyFactory.java`,
    layoutPropertyFactoryTemplate({properties: layoutProperties})
);

//Create types for the enum properties
const layoutEnumProperties = _(layoutProperties).filter({'type': 'enum'}).value();
const layoutPropertyTemplate = ejs.compile(fs.readFileSync('platform/android/scripts/layout_property.java.ejs', 'utf8'), {strict: true});
fs.writeFileSync(
    `platform/android/MapboxGLAndroidSDK/src/main/java/com/mapbox/mapboxsdk/style/properties/LayoutProperty.java`,
    layoutPropertyTemplate({properties: layoutEnumProperties})
);

