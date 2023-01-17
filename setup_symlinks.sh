#!/usr/bin/env bash

# Required for Git Bash on Windows.
# For this script to function on Windows you need to do one of the following:
# 1. Run this script with administrator escalation,
# 2. Open Local Security Policy (as admin):
#   * browse to Local Policies / User Rights Assignment / Create symbolic links
#   * click "Add user or group" and add your local user
export MSYS=winsymlinks:nativestrict

create_symlinks() {
	fabric="fabric"
	forge="forge"
	common="common"

  cd "$fabric" || exit 1
	if [[ ! -e "common" ]]; then
      ln -s "../$common" "./common"
	fi

  cd "../$forge" || exit 1
	if [[ ! -e "common" ]]; then
      ln -s "../$common" "./common"
	fi

	cd ..
}

create_symlinks api
create_symlinks core