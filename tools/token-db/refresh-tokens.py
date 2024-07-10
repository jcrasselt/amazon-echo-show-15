#!/usr/bin/env python3

import argparse
import os
import json
import uuid
import requests

def get_access_token(refresh_token):
    headers = {
        "Content-Type": "application/json"
    }

    body = {
        "app_name": "com.amazon.alexa",
        "source_token_type": "refresh_token",
        "source_token": refresh_token,
        "requested_token_type": "access_token"
    }

    res = requests.post("https://api.amazon.com/auth/token", headers=headers, data=json.dumps(body))
    return res
    
def get_access_cookies(refresh_token):
    headers = {
        "Content-Type": "application/json",
    }

    body = {
        "app_name": "com.amazon.alexa",
        "domain": "www.amazon.com",
        "source_token_type": "refresh_token",
        "source_token": refresh_token,
        "requested_token_type": "auth_cookies"
    }

    res = requests.post("https://api.amazon.com/auth/token", headers=headers, data=json.dumps(body))
    return res
  

def parse_args():
    parser = argparse.ArgumentParser(
                    prog="Token Refresher",
                    description="Takes a refresh token and returns access token or session cookies.")
    parser.add_argument("type",
                        choices=["token", "cookies"],
                        help="Access token or session cookies")
    parser.add_argument("refresh_token",
                        help="File containing access token")
    
    pargs = parser.parse_args()

    if not os.path.isfile(pargs.refresh_token):
        raise argparse.ArgumentTypeError("refresh_token should be a file")
    
    return pargs


def main():

    args = parse_args()

    with open(args.refresh_token, "r") as fd:
        refresh_token = fd.read().strip()

    if args.type == "token":
        res = get_access_token(refresh_token)
        access_token = res.json().get("access_token", "")
        print(access_token)
        if not access_token:
            print("WARNING: Could not extract access_token. Here is the API response instead:")
            print(json.dumps(res.json(), indent=2))

    elif args.type == "cookies":
        res = get_access_cookies(refresh_token)

        try:
            cookies = res.json()["response"]["tokens"]["cookies"]
            print(json.dumps(cookies, indent=2))
        except:
            print("WARNING: Could not extract cookies. Here is the API response instead:")
            print(json.dumps(res.json(), indent=2))

if __name__ == "__main__":
    main()
