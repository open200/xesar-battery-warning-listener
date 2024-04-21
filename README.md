# Xesar Battery Warning Listener

This is a simple Kotlin / Spring Boot application that uses [Xesar-Connect](https://github.com/open200/xesar-connect) to 
listen for battery empty events from the electric lock system 
[Xesar](https://www.evva.com/int-en/products/electronic-locking-systems-accesscontrol-systems/xesar/) from EVVA.

This application is intended as demonstration of how to use the Xesar-Connect library to listen for events from the Xesar
system. It contains two event listeners:

1. `LoggingListener`
2. `BatteryEmptyListener`

The `LoggingListener` will log the topics of all events received from the Xesar system.
The `BatteryEmptyListener` will listen for battery empty events and log the information about the installation point
where the event occurred.

## Pre-requisites

- JDK 21
- Xesar 3.0 or higher

## Getting started

1. **Xesar installation**:

To run this application, you need to have a Xesar installation and access to create users in the Xesar system.
We recommend to create a new user in the Xesar installation with minimal permissions and download the user's configuration.

2. **Clone the repository**:

```bash
git clone git@github.com:open200/xesar-battery-warning-listener.git
cd xesar-battery-warning-listener
```

3. **Update the application.yml file**:

For simply testing you could adjust the `application.yml` file with the path to the previously downloaded ZIP file.

```yaml
xesarconnect:
  credentials-zip-path: /path/to/your/credentials.zip
```

However, we don't recommend checking in the ZIP file to the repository. Instead, you can use a relative path to the ZIP file or
provide the path as an environment variable:

```bash
export XESARCONNECT_CREDENTIALSZIPPATH=/path/to/your/credentials.zip
```

4. **Build the application**:

```bash
./gradlew build
```

5. **Run the application**:

```bash
./gradlew bootRun
```


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Additional Resources

- [Xesar-Connect](https://github.com/open200/xesar-connect)
- [Xesar API Documentation](https://integrations.api.xesar.evva.com)

## Sponsor

This project is sponsored by [open200](https://open200.com).
