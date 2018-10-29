import React from 'react';

export default props => (
	<div className="form-group">
		<label htmlFor={props.id} className="col-sm-2 control-label">{props.label}</label>

		<div className="col-sm-10">
		<input type={props.type} className="form-control" id={props.id} placeholder={props.placeHolder}
			value={props.value} onChange={props.onChange}/>
		</div>
	</div>
);
