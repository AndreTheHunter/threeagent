# Test

## Browser

```sh
npx shadow-cljs watch test
````

Then go to <http://localhost:8021/>

## Render Test

```sh
#FIXME fails
# Error: Cannot find module 'express'
lein cljsbuild once render-test && node ./tests/render_test/run.js
```

## Test

```sh
npx shadow-cljs compile ci && npx karma start --single-run
```

# Development

```sh
lein figwheel dev
```

Then go to <http://localhost:3449/>
