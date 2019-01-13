# log-function
Demo riff function

```
pfs function create log --git-repo https://github.com/dturanski/log-function.git --image gcr.io/$GCP_PROJECT/log --verbose
pfs service invoke log  --json -- --data '{"data":"01/03/19 16:51:30"}'
```
