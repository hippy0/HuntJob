build:
	./gradlew installBootDist

run:
	./app/build/install/app-boot/bin/app

test:
	./gradlew test

lint:
	./gradlew checkstyleMain

.PHONY: build