# Grafana Docker image

This project builds a Docker image with the latest release build of Grafana.

## Running your Grafana container

Start your container binding the external port `3000`.

```
docker run -d --name=grafana -p 3000:3000 hub.xenit.eu/public/grafana
```

Try it out, default admin user is admin/admin.

## Configuring your Grafana container

All options defined in `conf/grafana.ini` can be overridden using environment variables by using the syntax 
`GF_<SectionName>_<KeyName>`. For example:

```
docker run \
  -d \
  -p 3000:3000 \
  --name=grafana \
  -e "GF_SERVER_ROOT_URL=http://grafana.server.name" \
  -e "GF_SECURITY_ADMIN_PASSWORD=secret" \
  hub.xenit.eu/public/grafana
```

## Grafana container with persistent storage (recommended)

```
# create a persistent volume for your data in /var/lib/grafana (database and plugins)
docker volume create grafana-storage

# start grafana
docker run \
  -d \
  -p 3000:3000 \
  --name=grafana \
  -v grafana-storage:/var/lib/grafana \
  hub.xenit.eu/public/grafana
```

## Installing Plugins for Grafana

Pass the plugins you want installed to docker with the `GF_INSTALL_PLUGINS` environment variable as a comma separated 
list. This will pass each plugin name to `grafana-cli plugins install ${plugin}` and install them when Grafana starts.

```
docker run \
  -d \
  -p 3000:3000 \
  --name=grafana \
  -e "GF_INSTALL_PLUGINS=grafana-clock-panel,grafana-simple-json-datasource" \
  hub.xenit.eu/public/grafana
```

## Running specific version of Grafana

```
# specify right tag, e.g. 5.4 - see Docker Hub for available tags
docker run \
  -d \
  -p 3000:3000 \
  --name grafana \
  hub.xenit.eu/public/grafana:5.4
```

## Configuring AWS credentials for CloudWatch support

```
docker run \
  -d \
  -p 3000:3000 \
  --name=grafana \
  -e "GF_AWS_PROFILES=default" \
  -e "GF_AWS_default_ACCESS_KEY_ID=YOUR_ACCESS_KEY" \
  -e "GF_AWS_default_SECRET_ACCESS_KEY=YOUR_SECRET_KEY" \
  -e "GF_AWS_default_REGION=us-east-1" \
  hub.xenit.eu/public/grafana
```

You may also specify multiple profiles to `GF_AWS_PROFILES` (e.g. `GF_AWS_PROFILES=default another`).

Supported variables:

- `GF_AWS_${profile}_ACCESS_KEY_ID`: AWS access key ID (required).
- `GF_AWS_${profile}_SECRET_ACCESS_KEY`: AWS secret access  key (required).
- `GF_AWS_${profile}_REGION`: AWS region (optional).
