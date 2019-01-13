# Automation Tiny Task for Whirl Software
- All Project config can be found at **config.properties** file

###Application
- Setup application via Docker from following instructions:
https://microservices-demo.github.io/docs/quickstart.html

###HOST
I'm as Windows user have default docker machine local address as from config.properties host
- http://192.168.99.100/
If u have *Unix like system execute fallowing command:

> docker-machine ip default 

in your Docker toolbox to get local IP, 
and change it in config.properties file

###BROWSER TYPE
- browser.type=Chrome as Selenide default

###WEBDRIVER WAITS
- API auth key designed as default : 
> Basic cG9zdG1hbjpwb3N0bWFu