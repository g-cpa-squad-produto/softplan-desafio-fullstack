export const styles = theme => ({
    appBarSpacer: theme.mixins.toolbar,
    root: {
        width: '100%',
        marginTop: theme.spacing.unit * 3,
        overflowX: 'auto'
    },
    content: {
        flexGrow: 1,
        padding: theme.spacing.unit * 3,
        height: '100vh',
        overflow: 'auto',
    },
    title: {
        margin: theme.spacing.unit * 2
    },
    noLinkDecoration: {
        textDecoration: 'none'
    },
    paddingSm: {
        padding: theme.spacing.unit
    },
    paddingMd: {
        padding: theme.spacing.unit * 2
    },
    paddingLg: {
        padding: theme.spacing.unit * 3
    },
    marginSm: {
        margin: theme.spacing.unit
    },
    marginMd: {
        margin: theme.spacing.unit * 2
    },
    marginLg: {
        margin: theme.spacing.unit * 3
    },
    marginBottomSm: {
        marginBottom: theme.spacing.unit
    },
    marginBottomMd: {
        marginBottom: theme.spacing.unit * 2
    },
    marginBottomLg: {
        marginBottom: theme.spacing.unit * 3
    },
    buttonAdd: {
        position: 'absolute',
        bottom: 0,
        right: 0
    },
    menu: {
        width: 200,
    }
});