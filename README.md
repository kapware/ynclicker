# ynclicker

FIXME

## Usage

### Run the application locally

`lein ring server`

### Packaging and running as standalone jar

```
lein do clean, ring uberjar
java -jar target/server.jar
```

### Packaging as war

`lein ring uberwar`

### Running for development (with CIDER REPL)

`lein with-profile dev ring server`

## License

Copyright ©  FIXME
