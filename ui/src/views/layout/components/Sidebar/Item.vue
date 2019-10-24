<script>
export default {
  name: 'MenuItem',
  functional: true,
  props: {
    meta: {
      type: Object,
      default: () => {
        return {
          title: '',
          icon: ''
        }
      }
    }
  },
  render(h, context) {
    const title  = context.props.meta.title
    const icon = 'el-icon-'+context.props.meta.icon+' menu-icon'
    const vnodes = []
    if (icon) {
      vnodes.push(<i class={icon}></i>)
    }

    if (title) {
      vnodes.push(<span slot='title'>{(title)}</span>)
    }
    return vnodes
  }
}
</script>
