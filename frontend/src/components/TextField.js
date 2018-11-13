import React from 'react'
import TextField from '@material-ui/core/TextField'

export default ({field, form: {touched, errors}, ...props}) => (
  <TextField
    className="ml-2 mr-2 mt-2 mb-2"
    variant="outlined"
    error={Boolean(touched[field.name] && errors[field.name])}
    helperText={touched[field.name] && errors[field.name]}
    {...field}
    {...props}
  />
)
