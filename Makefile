APP_PORT=1998

all:
	@cat Makefile

a:
	lein ancient

c:
	lein clean

ca:
	lein cljsbuild auto

co:
	lein cljsbuild once

docs/uberdoc.html:
	lein marg

d: docs/uberdoc.html
	open docs/uberdoc.html

ck:
	lein check

o:
	open http://localhost:$(APP_PORT)/

r:
	lein run $(APP_PORT)

s:
	vagrant ssh

t:
	lein test

tr:
	lein trampoline cljsbuild repl-listen

